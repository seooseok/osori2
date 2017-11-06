import React from 'react'
import Moment from 'moment'
import DateRangePicker from 'react-bootstrap-daterangepicker'

import 'react-bootstrap-daterangepicker/css/daterangepicker.css'

class IconInputRangeDate extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            ranges: {
                'Today': [Moment(), Moment()],
                'Yesterday': [Moment().subtract(1, 'days'), Moment().subtract(1, 'days')],
                'Last 7 Days': [Moment().subtract(6, 'days'), Moment()],
                'Last 30 Days': [Moment().subtract(29, 'days'), Moment()],
                'This Month': [Moment().startOf('month'), Moment().endOf('month')],
                'Last Month': [Moment().subtract(1, 'month').startOf('month'), Moment().subtract(1, 'month').endOf('month')]
            },
            startDate: this.props.startDate,
            endDate: this.props.endDate
        };
    }

    handleEvent(event, picker) {
        this.setState({
            startDate: picker.startDate,
            endDate: picker.endDate
        });

        this.props.onChange(this.state.startDate.format('YYYY-MM-DD'), this.state.endDate.format('YYYY-MM-DD'))
    }

    render() {
        return (
            <div className="input-group">
                <div className="input-group-addon">
                    <i className="fa fa-calendar"/>
                </div>
                <DateRangePicker startDate={this.state.startDate} endDate={this.state.endDate}
                                 ranges={this.state.ranges} onEvent={this.handleEvent.bind(this)}>
                    <input type="text" className="date-range-btn form-control pull-right"/>
                </DateRangePicker>
            </div>
        )
    }
}

export default IconInputRangeDate

IconInputRangeDate.defaultProps = {
    startDate: Moment().subtract(29, 'days'),
    endDate: Moment()
};
