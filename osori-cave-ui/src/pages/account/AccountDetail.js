import React from 'react'
import {Form, Text} from 'react-form';

class AccountDetail extends React.Component {
    constructor(props) {
        super(props);

        this.handleProfile()
    }

    handleProfile = () => {
        let profile = this.props.profile;
        if (profile === undefined) {
            profile = {
                id: '',
                loginId: '',
                name: '',
                email: '',
                phone: '',
                department: '',
                comment: ''
            }
        }
        this.state = {
            profile
        }
    };

    handleSubmit = (e) => {
        console.debug(e)
    };

    render() {
        let profilePanel;

        if (this.props.profile === undefined) {
            profilePanel = (
                <div>
                    <div className="callout callout-info">
                        <h4>Please select a row in the search results</h4>
                        <p>Shows the encrypted personal information of the account.</p>
                    </div>

                </div>
            )
        } else {
            profilePanel = (
                <Form onSubmit={this.handleSubmit}>
                    {
                        formApi => (
                            <form onSubmit={formApi.submitForm} id="modifyProfile">
                                <div className="form-group">
                                    <div className="input-group">
                                        <span className="input-group-addon"><i className="fa fa-user"/></span>
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
                    <h5 className="box-title">{this.state.profile.loginId} Profile</h5>
                </div>
                <div className="box-body">
                    {profilePanel}
                </div>
            </div>
        )
    }
}

export default AccountDetail

