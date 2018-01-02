import React from 'react'
import {Checkbox, Form, Radio, RadioGroup, Select, Text} from 'react-form'

import './addChildModal.css'

class AddChildModal extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            form: {
                depthType: 'NAVI'
            },
            domControl: {
                showUriInput: true,
                httpMethodInputs: [
                    {type: 'get', availInput: true},
                    {type: 'post', availInput: false},
                    {type: 'put', availInput: false},
                    {type: 'delete', availInput: false}
                ],
                showParamsInput: false
            },
            marginTop: Math.max(0, window.innerHeight / 4)
        }
    }

    handleSubmit = (formData) => {
        console.debug('add child: %s ', JSON.stringify(formData));

        this.props.onClose()
    };

    onClickUriTypeRadio = (e) => {
        let choice = e.target.id;

        switch (choice) {
            case 'navigation': {
                this.state.domControl = {
                    showUriInput: true,
                    httpMethodInputs: [
                        {type: 'get', availInput: true},
                        {type: 'post', availInput: false},
                        {type: 'put', availInput: false},
                        {type: 'delete', availInput: false}
                    ],
                    showParamsInput: false
                };
                break;
            }
            case 'function': {
                this.state.domControl = {
                    showUriInput: true,
                    httpMethodInputs: [
                        {type: 'get', availInput: true},
                        {type: 'post', availInput: true},
                        {type: 'put', availInput: true},
                        {type: 'delete', availInput: true}
                    ],
                    showParamsInput: false
                };
                break;
            }
            case 'parameter': {
                this.state.domControl = {
                    showUriInput: false,
                    showParamsInput: true
                };
                break;
            }
        }
    };

    render() {
        if (!this.props.show)
            return null;

        return (
            <div className="modal-open modal">
                <div className="modal-dialog" style={{marginTop: this.state.marginTop}}>
                    <div className="modal-content">
                        <div className="modal-header">
                            <button type="button" className="close" onClick={this.props.onClose} aria-label="Close">
                                <span aria-hidden="true">&times;</span></button>
                            <h4 className="modal-title">New child privilege URL</h4>
                        </div>
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
                                            {this.state.domControl.showUriInput &&
                                            <div className="form-horizontal">
                                                <div className="form-group">
                                                    <label className="col-sm-3 control-label">Uri</label>
                                                    <div className="col-sm-9">
                                                        <Text className="form-control" field="resource"
                                                              placeholder="Enter uri" autoComplete='resource'/>
                                                        <p className="text-muted">
                                                            <small><strong>Full Url:</strong></small>
                                                            /account/user/:id
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
                                                                              placeholder="Enter action title"/>
                                                                    </div>
                                                                )
                                                            }
                                                        })}
                                                    </div>
                                                </div>
                                            </div>
                                            }
                                            {this.state.domControl.showParamsInput &&
                                            <div className="form-horizontal">
                                                <div className="form-group">
                                                    <label className="col-sm-3 control-label">Control title</label>
                                                    <div className="col-sm-9">
                                                        <Text className="form-control" field="paramTitle"
                                                              placeholder="Enter title" autoComplete='paramTitle'/>
                                                    </div>
                                                </div>
                                                <div className="form-group">
                                                    <label className="col-sm-3 control-label">Convert Data to *</label>
                                                    <div className="col-sm-9">
                                                        <Checkbox field="convertAsterisk" className="checkbox"/>
                                                    </div>
                                                </div>
                                                <div className="form-group">
                                                    <label className="col-sm-3 control-label">Hide params</label>
                                                    <div className="col-sm-9">
                                                        <textarea class="form-control" rows="3"
                                                                  placeholder="Enter params"></textarea>
                                                        <p className="text-muted">
                                                            <small><strong>example:</strong></small>
                                                            foo,bar,id,name
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>
                                            }
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
                </div>
            </div>
        )
    }
}


export default AddChildModal


AddChildModal.defaultProps = {
    depthTypes: [
        {
            name: 'navigation',
            value: 'NAVI'
        },
        {
            name: 'function',
            value: 'FUNC'
        },
        {
            name: 'parameter',
            value: 'PARAM'
        },
    ]
};


