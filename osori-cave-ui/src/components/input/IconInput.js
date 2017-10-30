import React from 'react'

class IconInput extends React.Component {
    render() {
        return (
            <div class="input-group">
                <span className="input-group-addon"><i className={'fa ' + this.props.icon}></i></span>
                <input type={this.props.type} name={this.props.name} class="form-control"
                       placeholder={this.props.holder}></input>
            </div>
        )
    }
}

IconInput.defaultProps = {
    type: 'text'
}

export default IconInput
