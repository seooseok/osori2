import React from 'react'

import AddChildren from './AddChildren'

import './navigationModal.css'

class NavigationModal extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            marginTop: Math.max(0, window.innerHeight / 4)
        }
    }

    render() {
        if (!this.props.show)
            return null;

        return (
            <div className="modal-open modal">
                <div className="modal-dialog" style={{marginTop: this.state.marginTop}}>
                    <div className="modal-content">
                        <div className="modal-header">
                            <button type="button" className="close" onClick={this.props.onClose} aria-label="Close">
                                <span aria-hidden="true">&times;</span></button>
                            <h4 className="modal-title">{this.props.modalData.name}</h4>
                        </div>
                        <AddChildren modalData={this.props.modalData}
                                     onAdded={this.props.onAdded}
                                     onClose={this.props.onClose}
                        />
                    </div>
                </div>
            </div>
        )
    }
}

export default NavigationModal
