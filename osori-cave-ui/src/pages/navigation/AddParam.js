import React from 'react'
import {Checkbox, Form, Radio, RadioGroup, Select, Text} from 'react-form'

import './navigationModal.css'

class AddParam extends React.Component {
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
            <Form onSubmit={this.handleSubmit} defaultValues={this.state.form}>
                {
                    formApi => (
                        <form onSubmit={formApi.submitForm} id="saveForm">
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
                        </form>
                    )
                }
            </Form>
        )
    }
}


export default AddParam
