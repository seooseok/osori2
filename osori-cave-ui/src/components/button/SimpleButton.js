import React from 'react'

class SimpleButton extends React.Component {

    render() {
        let inputClassName = '';

        let size = this.props.size;
        let name = this.props.name;
        let flat = this.props.flat;
        let style = this.props.style;
        let disabled = this.props.disabled;
        let onClick = this.props.onClick;

        if (size) {
            inputClassName += ' btn-' + size
        }

        if (flat) {
            inputClassName += ' btn-flat'
        }

        if (style) {
            inputClassName += ' btn-' + style
        }

        if (disabled) {
            inputClassName += ' disabled'
        }

        return (
            <button type="button" className={'btn btn-block' + inputClassName} onClick={onClick}>{name}</button>
        )
    }
}

export default SimpleButton
