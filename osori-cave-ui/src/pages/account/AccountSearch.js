import React from 'react'
import {Button, FormControl, FormGroup, InputGroup} from 'react-bootstrap'
import {IconInputRangeDate} from '../../container/input'
import Moment from 'moment'

class AccountSearchComponent extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            startDate: Moment().subtract(29, 'days'),
            endDate: Moment()
        }
    }

    handleRangeEvent = (startDate, endDate) => {
        this.setState({
            startDate,
            endDate
        });
        console.debug('search range date is ' + startDate + ', ' + endDate)
    };

    handleButton = (e) => {
        console.debug('button click')
    };

    render() {
        return (
            <div className="box box-info">
                <div className="box-header with-border">
                    <h5 className="box-title">Search for Users</h5>
                </div>
                <div className="box-body">
                    <FormGroup>
                        <div className="col-md-2">
                            <InputGroup>
                                <InputGroup.Addon><i className="fa fa-laptop"/></InputGroup.Addon>
                                <FormControl type="text" placeholder="Login ID"/>
                            </InputGroup>
                        </div>
                        <div className="col-md-2">
                            <InputGroup>
                                <InputGroup.Addon><i className="fa fa-user-o"/></InputGroup.Addon>
                                <FormControl type="text" placeholder="Name"/>
                            </InputGroup>
                        </div>
                        <div className="col-md-3">
                            <IconInputRangeDate startDate={this.state.startDate} endDate={this.state.endDate}
                                                onChange={this.handleRangeEvent}/>
                        </div>
                        <div className="col-md-2">
                            <FormControl componentClass="select"
                                         defaultValue={this.props.accountStatusSelector.selected}>
                                {this.props.accountStatusSelector.options.map((option) => {
                                    return (
                                        <option key={option.value} value={option.value}>{option.name}</option>
                                    );
                                })}
                            </FormControl>
                        </div>
                        <div className="col-md-2 pull-right">
                            <div className="pull-right">
                                <Button onClick={this.handleButton} bsStyle="primary">Search</Button>
                            </div>
                        </div>
                    </FormGroup>
                </div>
            </div>
        )
    }
}

AccountSearchComponent.defaultProps = {
    accountStatusSelector: {
        selected: 'All Status',
        options: [
            {name: 'All Status', value: ''},
            {name: 'Allow', value: 'ALLOW'},
            {name: 'Reject', value: 'REJECT'},
            {name: 'Wait', value: 'WAIT'},
            {name: 'Expire', value: 'EXPIRE'}
        ]
    }
};

export default AccountSearchComponent
