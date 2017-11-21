import React from 'react'
import {bindActionCreators} from 'redux'
import {connect} from 'react-redux'
import {Form, Text} from 'react-form'
import {modifyOne} from '../../actions/account/account.modify'
import {expireOne} from '../../actions/account/account.expire'


class AccountDetail extends React.Component {

    handleSubmit = (formData) => {
        console.debug('modify account: %s ', JSON.stringify(formData));

        let url = this.props.detail.links.find(item => {
            return item.rel === 'self'
        }).href;

        if (url === undefined) {
            console.err('can\'t find detail url. links: ', JSON.stringify(this.props.detail.links));
        }

        this.props.modifyOne(url, formData);

        this.props.onChangeAccountDetail(formData)
    };

    handleExpire = () => {
        let url = this.props.detail.links.find(item => {
            return item.rel === 'self'
        }).href;

        if (url === undefined) {
            console.err('can\'t find detail url. links: ', JSON.stringify(this.props.detail.links));
        }

        this.props.expireOne(url);
        this.props.onExpireAccountDetail(this.props.detail.id)
    };




    render() {

        let panel = (
            <Form onSubmit={this.handleSubmit} defaultValues={this.props.detail}>
                {
                    formApi => (
                        <form onSubmit={formApi.submitForm}>
                            <div className="form-group">

                                <div className="input-group">
                                    <span className="input-group-addon"><i className="fa fa-laptop"/></span>
                                    <div className="form-control" disabled> {this.props.detail.loginId} </div>
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
                                    <Text className="form-control" field="department" placeholder="Enter department"/>
                                </div>
                                <br/>
                                <div className="input-group">
                                    <span className="input-group-addon"><i className="fa fa-sitemap"/></span>
                                    <Text className="form-control" field="position" placeholder="Enter position"/>
                                </div>
                                <br/>
                                <div className="input-group">
                                    <span className="input-group-addon"><i className="fa fa-comment"/></span>
                                    <Text className="form-control" field="comment" placeholder="Enter something else"/>
                                </div>
                                <br/>
                            </div>
                            <br/>
                            <div className="pull-left">
                                <button type="submit" className="btn btn-block btn-success">Modify</button>
                            </div>
                            <div className="pull-right">
                                <button type="button" onClick={this.handleExpire} className="btn btn-block btn-danger">
                                    Expire
                                </button>
                            </div>
                        </form>
                    )
                }
            </Form>
        );

        let noSelectPanel = (
            <div className="callout callout-info">
                <h4>Please select a row in the search results</h4>
                <p>Shows the encrypted personal information of the account.</p>
            </div>
        );

        let loadingPanel = (
            <form>
                <div className="form-group">
                    <div className="input-group">
                        <span className="input-group-addon"><i className="fa fa-laptop"/></span>
                        <div className="form-control"/>
                    </div>
                    <br/>
                    <div className="input-group">
                        <span className="input-group-addon"><i className="fa fa-user-o"/></span>
                        <div className="form-control"/>
                    </div>
                    <br/>
                    <div className="input-group">
                        <span className="input-group-addon"><i className="fa fa-envelope"/></span>
                        <div className="form-control"/>
                    </div>
                    <br/>
                    <div className="input-group">
                        <span className="input-group-addon"><i className="fa fa-phone"/></span>
                        <div className="form-control"/>
                    </div>
                    <br/>
                    <div className="input-group">
                        <span className="input-group-addon"><i className="fa fa-building"/></span>
                        <div className="form-control"/>
                    </div>
                    <br/>
                    <div className="input-group">
                        <span className="input-group-addon"><i className="fa fa-sitemap"/></span>
                        <div className="form-control"/>
                    </div>
                    <br/>
                    <div className="input-group">
                        <span className="input-group-addon"><i className="fa fa-comment"/></span>
                        <div className="form-control"/>
                    </div>
                    <br/>
                </div>
                <br/>
                <div className="pull-left">
                    <button type="submit" className="btn btn-block btn-success" disabled>Modify</button>
                </div>
                <div className="pull-right">
                    <button type="button" className="btn btn-block btn-danger" disabled>Expire</button>
                </div>
            </form>
        );


        if (this.props.detail === undefined && !this.props.isFetching) {
            panel = noSelectPanel
        }

        //FiXME: duplicate code
        if (this.props.detail === undefined && this.props.isFetching) {
            panel = loadingPanel
        }

        return (
            <div className="box">
                <div className="box-header with-border">
                    <h5 className="box-title"><i className="fa fa-fw fa-pencil-square-o"/> Account Profile</h5>
                </div>
                <div className="box-body">
                    {panel}
                </div>
                {
                    this.props.isFetching &&
                    <div className="overlay">
                        <i className="fa fa-refresh fa-spin"></i>
                    </div>
                }
            </div>
        )
    }
}


let mapStateToProps = (state) => {
    return {
        isFetching: state.accountDetail.isFetching,
        detail: state.accountDetail.payload
    };
};
const mapDispatchToProps = (dispatch) => bindActionCreators({modifyOne, expireOne}, dispatch);

export default connect(mapStateToProps, mapDispatchToProps)(AccountDetail)



