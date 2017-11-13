import React from 'react'
import {Form, Text} from 'react-form';

class AccountDetail extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            account: this.props.account
        }
    }

    handleSubmit = (e) => {
        console.debug(e)
    };

    render() {

        let accountPanel;

        if (this.props.account === undefined) {
            accountPanel = (
                <div>
                    <div className="callout callout-info">
                        <h4>Please select a row in the search results</h4>
                        <p>Shows the encrypted personal information of the account.</p>
                    </div>
                </div>
            )
        } else {
            accountPanel = (
                <Form onSubmit={this.handleSubmit} defaultValues={this.props.account}>
                    {
                        formApi => (
                            <form onSubmit={formApi.submitForm} id="modifyProfile">
                                <div className="form-group">
                                    <div className="input-group">
                                        <span className="input-group-addon"><i className="fa fa-laptop"/></span>
                                        <Text className="form-control" field="loginId" disabled/>
                                    </div>
                                    <br/>
                                    <div className="input-group">
                                        <span className="input-group-addon"><i className="fa fa-user-o"/></span>
                                        <Text className="form-control" field="name" placeholder="Enter Name"/>
                                    </div>
                                    <br/>
                                    <div className="input-group">
                                        <span className="input-group-addon"><i className="fa fa-envelope"/></span>
                                        <Text className="form-control" field="email" placeholder="Enter email"/>
                                    </div>
                                    <br/>
                                    <div className="input-group">
                                        <span className="input-group-addon"><i className="fa fa-phone"/></span>
                                        <Text className="form-control" field="phone" placeholder="Enter phone"/>
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
                                    <button type="submit" className="btn btn-block btn-success">Search</button>
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
                    {accountPanel}
                </div>
            </div>
        )
    }
}

export default AccountDetail

