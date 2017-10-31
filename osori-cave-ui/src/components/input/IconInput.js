import React from 'react'

class IconInput extends React.Component {

    render() {
        let type = this.props.type;
        let name = this.props.name;
        let icon = this.props.icon;
        let holder = this.props.holder;
        let onChange = this.props.onChange;

        return (
            <div className="input-group">
                <span className="input-group-addon"><i className={'fa ' + icon}/></span>
                <input type={type} name={name} className="form-control" placeholder={holder} onChange={onChange}/>
            </div>
        )
    }
}

IconInput.defaultProps = {
    type: 'text'
};

export default IconInput
