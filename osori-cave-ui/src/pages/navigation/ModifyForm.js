import React from 'react'
import {bindActionCreators} from 'redux'
import {connect} from 'react-redux'

import {Form, Radio, RadioGroup, Select, Text} from 'react-form'

import {modifyOne} from "../../actions/navigation/navigation.modify";
import * as hateoas from "../../util/Utils";

class ModifyForm extends React.Component {

    handleSubmit = (formData) => {
        let url = hateoas.findUrl(formData, 'self');

        this.props.modifyOne(url, formData);
        this.props.onModified(formData, this.props.modalData);
    };

    render() {
        return (
            <div className="modal-body">
                <Form onSubmit={this.handleSubmit} defaultValues={this.props.modalData.node}>
                    {
                        formApi => (
                            <form onSubmit={formApi.submitForm} id="saveForm">
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
                                            <p className="text-muted">
                                                <small>
                                                    <strong>Before
                                                        Url</strong>: {this.props.modalData.node.fullUri}
                                                </small>
                                            </p>
                                            <Text className="form-control" field="resource"
                                                  placeholder="Enter uri" autoComplete='resource'/>
                                        </div>
                                    </div>
                                    <div className="form-group">
                                        <label className="col-sm-3 control-label">Name</label>
                                        <div className="col-sm-9">
                                            <Text className="form-control" field="name"
                                                  placeholder="Enter title" autoComplete='name'/>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        )
                    }
                </Form>
            </div>
        )
    }
}

const mapDispatchToProps = (dispatch) => bindActionCreators({modifyOne}, dispatch);

export default connect(null, mapDispatchToProps)(ModifyForm)

ModifyForm.defaultProps = {
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
