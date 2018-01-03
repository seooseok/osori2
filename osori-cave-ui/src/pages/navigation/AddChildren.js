import React from 'react'
import {bindActionCreators} from 'redux'
import {connect} from 'react-redux'

import {Form, Radio, RadioGroup, Select, Text} from 'react-form'
import {addAll} from "../../actions/navigation/navigation.add.all";

class AddChildren extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            form: {
                depthType: 'NAVI'
            },
            domControl: {
                httpMethodInputs: [
                    {type: 'get', availInput: true},
                    {type: 'post', availInput: false},
                    {type: 'put', availInput: false},
                    {type: 'delete', availInput: false}
                ]
            }
        }
    }

    handleSubmit = (formData) => {
        console.debug('add child: %s ', JSON.stringify(formData));
        let params = [];

        if (formData.getTitle) {
            params.push({
                parentId: this.props.modalData.parentNode.id,
                name: formData.getTitle,
                resource: formData.resource,
                depthType: formData.depthType,
                methodType: 'GET'
            })
        }

        if (formData.putTitle) {
            params.push({
                parentId: this.props.modalData.parentNode.id,
                name: formData.putTitle,
                resource: formData.resource,
                depthType: formData.depthType,
                methodType: 'PUT'
            })
        }

        if (formData.postTitle) {
            params.push({
                parentId: this.props.modalData.parentNode.id,
                name: formData.postTitle,
                resource: formData.resource,
                depthType: formData.depthType,
                methodType: 'POST'
            })
        }

        if (formData.deleteTitle) {
            params.push({
                parentId: this.props.modalData.parentNode.id,
                name: formData.deleteTitle,
                resource: formData.resource,
                depthType: formData.depthType,
                methodType: 'DELETE'
            })
        }

        this.props.addAll(params);
        this.props.onAdded(formData, this.props.modalData);
    };

    onClickUriTypeRadio = (e) => {
        let choice = e.target.id;

        switch (choice) {
            case 'navigation': {
                this.state.domControl = {
                    httpMethodInputs: [
                        {type: 'get', availInput: true},
                        {type: 'post', availInput: false},
                        {type: 'put', availInput: false},
                        {type: 'delete', availInput: false}
                    ]
                };
                break;
            }
            case 'function': {
                this.state.domControl = {
                    httpMethodInputs: [
                        {type: 'get', availInput: true},
                        {type: 'post', availInput: true},
                        {type: 'put', availInput: true},
                        {type: 'delete', availInput: true}
                    ]
                };
                break;
            }
        }
    };

    render() {
        return (
            <div>
                <div className="modal-body">
                    <Form onSubmit={this.handleSubmit} defaultValues={this.state.form}>
                        {
                            formApi => (
                                <form onSubmit={formApi.submitForm} id="addChildForm">
                                    <div className="form-horizontal">
                                        <div className="form-group">
                                            <label className="col-sm-3 control-label">Privilege type</label>
                                            <div className="col-sm-9">
                                                <RadioGroup field="depthType">
                                                    {group => (
                                                        <div>
                                                            {this.props.depthTypes.map((depthType, i) => {
                                                                return (
                                                                    <label key={depthType.name}
                                                                           className="radio-inline">
                                                                        <Radio group={group}
                                                                               value={depthType.value}
                                                                               id={depthType.name}
                                                                               onClick={this.onClickUriTypeRadio}/>
                                                                        {depthType.name.charAt(0).toUpperCase() + depthType.name.slice(1)}
                                                                    </label>
                                                                )
                                                            })}
                                                        </div>
                                                    )}
                                                </RadioGroup>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="form-horizontal">
                                        <div className="form-group">
                                            <label className="col-sm-3 control-label">Uri</label>
                                            <div className="col-sm-9">
                                                <Text className="form-control" field="resource"
                                                      placeholder="Enter uri" autoComplete='resource'/>
                                                <p className="text-muted">
                                                    <small>
                                                        <strong>Full
                                                            Url</strong>: {this.props.modalData.parentNode.fullUri}{formApi.values.resource}
                                                    </small>
                                                </p>
                                            </div>
                                        </div>
                                        <div className="form-group">
                                            <label className="col-sm-3 control-label">API Title</label>
                                            <div className="col-sm-9">
                                                {this.state.domControl.httpMethodInputs.map((httpMethod, i) => {
                                                    if (httpMethod.availInput) {
                                                        return (
                                                            <div key={httpMethod.type} className="input-group"
                                                                 style={{padding: "2px"}}>
                                                                        <span
                                                                            className="input-group-addon span-http-method">{httpMethod.type.toUpperCase()}</span>
                                                                <Text className="form-control input-sm"
                                                                      field={httpMethod.type + "Title"}
                                                                      placeholder="Enter API title"/>
                                                            </div>
                                                        )
                                                    }
                                                })}
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            )
                        }
                    </Form>
                </div>
                <div className="modal-footer">
                    <button type="button" className="btn btn-default pull-left" onClick={this.props.onClose}>
                        Close
                    </button>
                    <button type="submit" form="addChildForm" className="btn btn-success">Save</button>
                </div>
            </div>
        )
    }
}


const mapDispatchToProps = (dispatch) => bindActionCreators({addAll}, dispatch);

export default connect(null, mapDispatchToProps)(AddChildren)

AddChildren.defaultProps = {
    depthTypes: [
        {
            name: 'navigation',
            value: 'NAVI'
        },
        {
            name: 'function',
            value: 'FUNC'
        }
    ]
};
