import React from 'react'
import {bindActionCreators} from 'redux'
import {connect} from 'react-redux'
import {Form, Text} from 'react-form';
import {modifyOne} from "../../actions/account/account.modify";

//FIXME: Form을 아예 re render 하는 방법을 모르겠다.
class AccountDetail extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            detail: {}
        };
    }

    handleSubmit = (formData) => {
        console.debug('modify account: %s ', JSON.stringify(formData));

        let url = this.state.detail.links.find(item => {
            return item.rel === 'self'
        }).href;

        if (url === undefined)
            console.err('can\'t find detail url. links: ', JSON.stringify(this.state.detail.links));

        this.props.modifyOne(url, formData)

    };

    componentWillReceiveProps(nextProps) {
        console.debug("componentWillReceiveProps: " + JSON.stringify(nextProps));
        if (nextProps.payload !== undefined) {
            this.setState({
                detail: nextProps.payload
            });
        }
    }

    render() {
        let panel;
        if (this.props.payload === undefined) {
            panel = (
                <div>
                    <div className="callout callout-info">
                        <h4>Please select a row in the search results</h4>
                        <p>Shows the encrypted personal information of the account.</p>
                    </div>
                </div>
            )
        } else {
            panel = (
                <Form onSubmit={this.handleSubmit} defaultValues={this.state.detail}>
                    {
                        formApi => (
                            <form onSubmit={formApi.submitForm} id="modifyProfile">
                                <div className="form-group">
                                    <div className="input-group">
                                        <span className="input-group-addon"><i className="fa fa-laptop"/></span>
                                        <div className="form-control" disabled>
                                            {this.state.detail.loginId}
                                        </div>
                                    </div>
                                    <br/>
                                    <div className="input-group">
                                        <span className="input-group-addon"><i className="fa fa-user-o"/></span>
                                        <Text className="form-control" field="name" placeholder="Enter Name"
                                              autoComplete='name'/>
                                    </div>
                                    <br/>
                                    <div className="input-group">
                                        <span className="input-group-addon"><i className="fa fa-envelope"/></span>
                                        <Text className="form-control" field="email" placeholder="Enter email"
                                              autoComplete='email'/>
                                    </div>
                                    <br/>
                                    <div className="input-group">
                                        <span className="input-group-addon"><i className="fa fa-phone"/></span>
                                        <Text className="form-control" field="phone" placeholder="Enter phone"
                                              autoComplete='tel'/>
                                    </div>
                                    <br/>
                                    <div className="input-group">
                                        <span className="input-group-addon"><i className="fa fa-building"/></span>
                                        <Text className="form-control" field="department"
                                              placeholder="Enter department"/>
                                    </div>
                                    <br/>
                                    <div className="input-group">
                                        <span className="input-group-addon"><i className="fa fa-comment"/></span>
                                        <Text className="form-control" field="comment"
                                              placeholder="Enter something else"/>
                                    </div>
                                    <br/>
                                </div>
                                <br/>
                                <div className="pull-left">
                                    <button type="submit" className="btn btn-block btn-success">Modify</button>
                                </div>
                                <div className="pull-right">
                                    <button type="button" className="btn btn-block btn-danger">Expire</button>
                                </div>
                            </form>
                        )
                    }
                </Form>
            )
        }
        return (
            <div className="box">
                <div className="box-header with-border">
                    <h5 className="box-title"><i className="fa fa-fw fa-pencil-square-o"/> Account Profile</h5>
                </div>
                <div className="box-body">
                    {panel}
                </div>
            </div>
        )
    }
}


let mapStateToProps = (state) => {
    return {
        payload: state.accountDetail.payload
    };
};
const mapDispatchToProps = (dispatch) => bindActionCreators({modifyOne}, dispatch);

export default connect(mapStateToProps, mapDispatchToProps)(AccountDetail)



