import React from 'react'
import {Button, FormControl, FormGroup, InputGroup} from 'react-bootstrap'

class AccountDetail extends React.Component {
    constructor(props) {
        super(props);

        this.handleProfile()
    }

    handleProfile = () => {
        let profile = this.props.profile
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
                <form>
                    <FormGroup>
                        <InputGroup>
                            <InputGroup.Addon><i className="fa fa-user"/></InputGroup.Addon>
                            <FormControl type="text" defaultValue={this.state.profile.name} placeholder="Enter name"/>
                        </InputGroup>
                        <br/>
                        <InputGroup>
                            <InputGroup.Addon><i className="fa fa-envelope"/></InputGroup.Addon>
                            <FormControl type="text" defaultValue={this.state.profile.email} placeholder="Enter email"/>
                        </InputGroup>
                        <br/>
                        <InputGroup>
                            <InputGroup.Addon><i className="fa fa-phone"/></InputGroup.Addon>
                            <FormControl type="text" defaultValue={this.state.profile.phone} placeholder="Enter phone"/>
                        </InputGroup>
                        <br/>
                        <InputGroup>
                            <InputGroup.Addon><i className="fa fa-building"/></InputGroup.Addon>
                            <FormControl type="text" defaultValue={this.state.profile.department}
                                         placeholder="Enter department"/>
                        </InputGroup>
                        <br/>
                        <InputGroup>
                            <InputGroup.Addon><i className="fa fa-comment"/></InputGroup.Addon>
                            <FormControl type="text" defaultValue={this.state.profile.comment}
                                         placeholder="Enter something else"/>
                        </InputGroup>
                        <br/>
                    </FormGroup>
                    <br/>
                    <div className="pull-left">
                        <Button bsStyle="success">Save</Button>
                    </div>
                    <div className="pull-right">
                        <Button bsStyle="danger">Expire</Button>
                    </div>
                </form>
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

