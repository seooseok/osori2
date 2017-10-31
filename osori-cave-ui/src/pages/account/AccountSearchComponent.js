import React from 'react'
import {IconInput, IconInputRangeDate} from '../../components/input'
import {SimpleSelect} from '../../components/select'
import {SimpleButton} from '../../components/button'
import Moment from 'moment'

class AccountSearchComponent extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            loginId: '',
            name: '',
            startDate: Moment().subtract(29, 'days'),
            endDate: Moment(),
            status: ''
        }
    }

    handleInputID = (e) => {
        this.setState({
            loginId: e.target.value
        });
        console.debug('input loginId is ' + e.target.value)
    };

    handleInputName = (e) => {
        this.setState({
            name: e.target.value
        });
        console.debug('input name is ' + e.target.value)
    };


    handleRangeEvent = (startDate, endDate) => {
        this.setState({
            startDate,
            endDate
        });
        console.debug('search range date is ' + startDate + ', ' + endDate)
    };

    handleStatus = (e) => {
        this.setState({
            status: e.target.value
        });
        console.debug('status is ' + e.target.value)
    };

    handleButton = (e) => {
        console.debug('button click')
    };

    render() {
        return (
            <div className="box box-info">
                <div className="box-header with-border">
                    <h3 className="box-title">Search for Users</h3>
                </div>
                <div className="box-body">
                    <div className="col-md-2">
                        <IconInput icon="fa-laptop" name="loginId" holder="Login ID" onChange={this.handleInputID}/>
                    </div>
                    <div className="col-md-2">
                        <IconInput icon="fa-user" name="name" holder="Name" onChange={this.handleInputName}/>
                    </div>
                    <div className="col-md-3">
                        <IconInputRangeDate startDate={this.state.startDate} endDate={this.state.endDate}
                                            onChange={this.handleRangeEvent}/>
                    </div>
                    <div className="col-md-3">
                        <SimpleSelect onChange={this.handleStatus}/>
                    </div>
                    <div className="col-md-2">
                        <div className="pull-right">
                            <SimpleButton name="Search" style="primary" flat="true" onClick={this.handleButton}/>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

SimpleSelect.defaultProps = {
    selected: 'All Status',
    options: [
        {name: 'All Status', value: ''},
        {name: 'Allow', value: 'ALLOW'},
        {name: 'Reject', value: 'REJECT'},
        {name: 'Wait', value: 'WAIT'},
        {name: 'Expire', value: 'EXPIRE'}
    ]
};

export default AccountSearchComponent
