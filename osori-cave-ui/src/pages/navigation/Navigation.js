import React from 'react'
import {bindActionCreators} from 'redux'
import {connect} from 'react-redux'
import ContentNav from '../components/ContentNav'
import SortableTree, {addNodeUnderParent, getTreeFromFlatData, removeNodeAtPath} from 'react-sortable-tree'

import {AddChildModal} from '.'
import {findAll} from '../../actions/navigation/navigation.list'


import './navigation.css'

const getNodeKey = ({treeIndex}) => treeIndex;

class Navigation extends React.Component {
    constructor(props) {
        super(props);

        this.props.findAll();

        this.state = {
            isOpenAddModal: false,
            treeData: [],
        };
    }

    componentWillReceiveProps(nextProps) {
        if (nextProps.payload !== undefined) {
            this.setState({
                treeData: getTreeFromFlatData({
                    flatData: nextProps.payload.content.map(node => ({
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


    onAdded = (formData, modalData) => {
        let path = modalData.nodePath;

        if (formData.getTitle) {
            this.setState(state => ({
                treeData: addNodeUnderParent({
                    treeData: state.treeData,
                    parentKey: path[path.length - 1],
                    expandParent: true,
                    getNodeKey,
                    newNode: {
                        title: formData.getTitle,
                        subtitle: modalData.parentNode.resource + formData.resource,
                        methodType: 'GET',
                        depthType: formData.depthType
                    },
                }).treeData,
            }))
        }

        if (formData.putTitle) {
            this.setState(state => ({
                treeData: addNodeUnderParent({
                    treeData: state.treeData,
                    parentKey: path[path.length - 1],
                    expandParent: true,
                    getNodeKey,
                    newNode: {
                        title: formData.putTitle,
                        subtitle: modalData.parentNode.resource + formData.resource,
                        methodType: 'PUT',
                        depthType: formData.depthType
                    },
                }).treeData,
            }))
        }

        if (formData.postTitle) {
            this.setState(state => ({
                treeData: addNodeUnderParent({
                    treeData: state.treeData,
                    parentKey: path[path.length - 1],
                    expandParent: true,
                    getNodeKey,
                    newNode: {
                        title: formData.postTitle,
                        subtitle: modalData.parentNode.resource + formData.resource,
                        methodType: 'POST',
                        depthType: formData.depthType
                    },
                }).treeData,
            }))
        }

        if (formData.deleteTitle) {
            this.setState(state => ({
                treeData: addNodeUnderParent({
                    treeData: state.treeData,
                    parentKey: path[path.length - 1],
                    expandParent: true,
                    getNodeKey,
                    newNode: {
                        title: formData.deleteTitle,
                        subtitle: modalData.parentNode.resource + formData.resource,
                        methodType: 'DELETE',
                        depthType: formData.depthType
                    },
                }).treeData,
            }))
        }
    };

    toggleModalByAddChild = (parentNode, path) => {
        this.setState({
            isOpenAddModal: !this.state.isOpenAddModal,
            modalData: {
                parentNode: parentNode,
                nodePath: path
            }
        })
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
                                            case 'GET':
                                                labelColor = 'label-primary';
                                                break;
                                            case 'POST':
                                                labelColor = 'label-success';
                                                break;
                                            case 'PUT':
                                                labelColor = 'label-warning';
                                                break;
                                            case 'DELETE':
                                                labelColor = 'label-danger';
                                                break;
                                            default:
                                                labelColor = 'label-info'
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
                                                                this.toggleModalByAddChild(node, path)
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
                <AddChildModal show={this.state.isOpenAddModal}
                               modalData={this.state.modalData}
                               onAdded={this.onAdded}
                               onClose={this.toggleModalByAddChild}/>
            </div>
        )
    }
}

const mapStateToProps = (state) => ({
    isFetching: state.navigationList.isFetching,
    payload: state.navigationList.payload
});

const mapDispatchToProps = (dispatch) => bindActionCreators({findAll}, dispatch);

export default connect(mapStateToProps, mapDispatchToProps)(Navigation)

