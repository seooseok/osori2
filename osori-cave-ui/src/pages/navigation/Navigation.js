import React from 'react'
import {bindActionCreators} from 'redux'
import {connect} from 'react-redux'
import ContentNav from '../components/ContentNav'
import SortableTree, {addNodeUnderParent, getTreeFromFlatData, removeNodeAtPath} from 'react-sortable-tree'

import {AddChildModal} from '.'

import {fetch} from '../../actions/navigation/navigation.list'


import './navigation.css'

const getNodeKey = ({treeIndex}) => treeIndex;

class Navigation extends React.Component {
    constructor(props) {
        super(props);

        this.props.fetch();

        this.state = {
            isOpenAddModal: true,
            treeData: [],
        };
    }

    componentWillReceiveProps(nextProps) {
        console.debug("componentWillReceiveProps: " + JSON.stringify(nextProps));
        if (nextProps.payload !== undefined) {
            this.setState({
                treeData: getTreeFromFlatData({
                    flatData: nextProps.payload.map(node => ({
                        ...node,
                        title: node.name,
                        subtitle: node.fullUri
                    })),
                    getKey: node => node.id,
                    getParentKey: node => node.parentId,
                    rootKey: null
                })
            });
        }
    }

    toggleAddModal = (path) => {
        this.setState({
            isOpenAddModal: !this.state.isOpenAddModal
        })
    };

    addChild = (path) => {
        this.setState(state => ({
            treeData: addNodeUnderParent({
                treeData: state.treeData,
                parentKey: path[path.length - 1],
                expandParent: true,
                getNodeKey,
                newNode: {
                    title: 'test',
                },
            }).treeData,
        }))
    };

    removeChild = (node, path) => {
        this.setState(state => ({
            treeData: removeNodeAtPath({
                treeData: state.treeData,
                path,
                getNodeKey,
            }),
        }))
    };

    render() {
        return (
            <div>
                <ContentNav category="Navigation" name="Navigation Configuration"
                            description="Manage URLs to verify permissions by tree structure"/>
                <section className="content">
                    <div className="box box-info">
                        <div className="box-header with-border">
                            <h3 className="box-title">URL Tree Management</h3>
                        </div>
                        <div className="box-body">
                            <div style={{height: 500}}>
                                <SortableTree
                                    treeData={this.state.treeData}
                                    onChange={treeData => this.setState({treeData})}
                                    generateNodeProps={({node, path}) => {

                                        let labelColor = '';
                                        switch (node.methodType) {
                                            case 'GET': {
                                                labelColor = 'label-primary';
                                                break;
                                            }
                                            case 'POST': {
                                                labelColor = 'label-success';
                                                break;
                                            }
                                            case 'PUT': {
                                                labelColor = 'label-warning';
                                                break;
                                            }
                                            case 'DELETE': {
                                                labelColor = 'label-danger';
                                                break;
                                            }
                                            default: {
                                                labelColor = 'label-info'
                                            }
                                        }

                                        return ({
                                            buttons: [
                                                <span
                                                    className="label label-default">{node.depthType && node.depthType.substring(0, 1)}</span>,
                                                <span className={"label " + labelColor}>{node.methodType}</span>,
                                                <div className="btn-group">
                                                    <button type="button" className="btn btn-info btn-xs">Detail
                                                    </button>
                                                    <button type="button"
                                                            className="btn btn-info btn-xs dropdown-toggle"
                                                            data-toggle="dropdown" aria-expanded="false">
                                                        <span className="caret"></span>
                                                        <span className="sr-only">Toggle Dropdown</span>
                                                    </button>
                                                    <ul className="dropdown-menu" role="menu">
                                                        <li>
                                                            <a onClick={(e) => {
                                                                   e.preventDefault();
                                                                   this.toggleAddModal(path)
                                                               }}>Add Child
                                                            </a>
                                                        </li>
                                                        <li className="divider"></li>
                                                        <li>
                                                            <a onClick={(e) => {
                                                                   e.preventDefault();
                                                                   this.removeChild(node, path)
                                                               }}>Remove
                                                            </a>
                                                        </li>
                                                    </ul>
                                                </div>
                                            ]
                                        })
                                    }}
                                />
                            </div>
                        </div>
                    </div>
                </section>
                <AddChildModal show={this.state.isOpenAddModal} onClose={this.toggleAddModal}/>
            </div>
        )
    }
}

const mapStateToProps = (state) => ({
    isFetching: state.navigationList.isFetching,
    payload: state.navigationList.payload
});

const mapDispatchToProps = (dispatch) => bindActionCreators({fetch}, dispatch);

export default connect(mapStateToProps, mapDispatchToProps)(Navigation)

