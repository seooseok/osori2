import React from 'react'
import {IconInputRangeDate} from '../../container/input'
import Moment from 'moment'
import {Form, Select, Text} from 'react-form';

class AccountSearchComponent extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            startDate: Moment().subtract(29, 'days'),
            endDate: Moment()
        }
    }

    handleSubmit = (e) => {
        console.debug(e)
    };

    render() {
        return (
            <div className="box box-info">
                <div className="box-header with-border">
                    <h5 className="box-title">Search for Users</h5>
                </div>
                <div className="box-body">
                    <Form onSubmit={this.handleSubmit}
                          defaultValues={
                              {
                                  startDate: this.state.startDate.format("YYYY-MM-DD"),
                                  endDate: this.state.endDate.format("YYYY-MM-DD")
                              }
                          }>
                        {
                            formApi => (
                                <form onSubmit={formApi.submitForm} id="search">
                                    <div className="form-group">
                                        <div className="col-md-2">
                                            <div className="input-group">
                                                <span className="input-group-addon"><i className="fa fa-laptop"/></span>
                                                <Text className="form-control" field="loginId" placeholder="Login ID"/>
                                            </div>
                                        </div>
                                        <div className="col-md-2">
                                            <div className="input-group">
                                                <span className="input-group-addon"><i className="fa fa-user-o"/></span>
                                                <Text className="form-control" field="name" placeholder="Name"/>
                                            </div>
                                        </div>
                                        <div className="col-md-3">
                                            <IconInputRangeDate startDate={this.state.startDate}
                                                                endDate={this.state.endDate}
                                                                onChange={(startDate, endDate) => {
                                                                    formApi.setValue('startDate', startDate);
                                                                    formApi.setValue('endDate', endDate);
                                                                }}/>
                                        </div>
                                        <div className="col-md-2">
                                            <Select className="form-control" field="status"
                                                    options={this.props.statusSelector.options}/>
                                        </div>
                                        <div className="col-md-2 pull-right">
                                            <div className="pull-right">
                                                <button type="submit" className="btn btn-block btn-primary">Search
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            )}
                    </Form>
                </div>
            </div>
        )
    }
}

AccountSearchComponent.defaultProps = {
    statusSelector: {
        options: [
            {label: 'All Status', value: ''},
            {label: 'Allow', value: 'ALLOW'},
            {label: 'Reject', value: 'REJECT'},
            {label: 'Wait', value: 'WAIT'},
            {label: 'Expire', value: 'EXPIRE'}
        ]
    }
};

export default AccountSearchComponent
