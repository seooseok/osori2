import React from 'react'

class SimpleSelect extends React.Component {
    render() {
        let selected = this.props.selected;
        let options = this.props.options;
        let onChange = this.props.onChange;

        return (
            <div className="form-group">
                <div className="col-sm-8">
                    <select className="form-control" defaultValue={selected} onChange={onChange}>
                        {options.map((option) => {
                            return (
                                <option key={option.value} value={option.value}>{option.name}</option>
                            );
                        })}
                    </select>
                </div>
            </div>
        )
    }
}

export default SimpleSelect
