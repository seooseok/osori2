import React from 'react'

class DateInput extends React.Component {
    render() {
        return (
            <div className="input-group">
                <div className="input-group-addon">
                    <i className="fa fa-calendar"></i>
                </div>
                <input type="text" class="form-control"></input>
            </div>
        )
    }
}

export default DateInput
