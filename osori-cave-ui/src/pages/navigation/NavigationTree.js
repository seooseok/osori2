import React from 'react'
import {bindActionCreators} from 'redux'
import {connect} from 'react-redux'
import SortableTree, {getTreeFromFlatData} from 'react-sortable-tree'

import {NavigationModal} from "."
import {remove} from "../../actions/navigation/navigation.remove";

import * as hateoas from "../../util/HateoasUtil";

const getNodeKey = ({treeIndex}) => treeIndex;

class NavigationTree extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            isOpenModal: false,
            treeData: []
        };
    }

    componentWillReceiveProps(nextProps) {
        if (nextProps.payload !== undefined) {
            this.setState({
                treeData: getTreeFromFlatData({
                    flatData: nextProps.payload.content.map(node => ({
                        ...node,
                        title: node.name,
                        subtitle: node.fullUri,
                        expanded: true
                    })),
                    getKey: node => node.id,
                    getParentKey: node => node.parentId,
                    rootKey: null
                })
            });
        }
    }

    onOffModal = () => {
        this.setState({
            isOpenModal: !this.state.isOpenModal,
        })
    };


    addChildren = (parentNode, path) => {
        this.onOffModal();
        this.setState({
            modalData: {
                name: "New child privilege URL",
                component: "AddChildrenForm",
                parentNode: parentNode,
                nodePath: path
            }
        })
    };

    modifyChild = (node, path) => {
        this.onOffModal();
        this.setState({
            modalData: {
                name: "Modify privilege URL",
                component: "ModifyForm",
                node: node,
                nodePath: path
            }
        })
    };

    remove = (node, path) => {
        let url = hateoas.findUrl(node, 'self');
        this.props.remove(url);
    };

    render() {
        return (
            <div>
                <div className="box box-info">
                    <div className="box-header with-border">
                        <h3 className="box-title">URL Tree Management</h3>
                    </div>
                    <div className="box-body" style={{height: 600}}>
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
                                            className="label label-info">{node.depthType && node.depthType.substring(0, 1)}</span>,
                                        <span className={"label " + labelColor}>{node.methodType}</span>,
                                        <div className="btn-group">
                                            <button type="button" className="btn btn-default btn-xs"
                                                    onClick={(e) => {
                                                        e.preventDefault();
                                                        this.modifyChild(node, path)
                                                    }}>Modify
                                            </button>
                                            <button type="button"
                                                    className="btn btn-default btn-xs dropdown-toggle"
                                                    data-toggle="dropdown" aria-expanded="false">
                                                <span className="caret"></span>
                                                <span className="sr-only">Toggle Dropdown</span>
                                            </button>
                                            <ul className="dropdown-menu" role="menu">
                                                <li>
                                                    <a onClick={(e) => {
                                                        e.preventDefault();
                                                        this.addChildren(node, path)
                                                    }}>Add Child
                                                    </a>
                                                </li>
                                                <li className="divider"></li>
                                                <li>
                                                    <a onClick={(e) => {
                                                        e.preventDefault();
                                                        this.remove(node, path)
                                                    }}>Remove
                                                    </a>
                                                </li>
                                            </ul>
                                        </div>
                                    ]
                                })
                            }}
                        />
                        {
                            this.props.isFetching &&
                            <div className="overlay">
                                <i className="fa fa-refresh fa-spin"></i>
                            </div>
                        }
                    </div>
                </div>
                <NavigationModal show={this.state.isOpenModal}
                                 modalData={this.state.modalData}
                                 onClose={this.onOffModal}/>
            </div>
        )
    }
}

const mapStateToProps = (state) => ({
    isFetching: state.navigationList.isFetching,
    payload: state.navigationList.payload
});

const mapDispatchToProps = (dispatch) => bindActionCreators({remove}, dispatch);

export default connect(mapStateToProps, mapDispatchToProps)(NavigationTree)
