import React from 'react'

import {AddChildrenForm, ModifyForm} from '.'

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
                        {
                            this.props.modalData.component === "AddChildrenForm" ?
                                <AddChildrenForm modalData={this.props.modalData} onAdded={this.props.onAdded}/>
                                :
                                <ModifyForm modalData={this.props.modalData} onModified={this.props.onModified}/>
                        }

                        <div className="modal-footer">
                            <button type="button" className="btn btn-default pull-left" onClick={this.props.onClose}>
                                Close
                            </button>
                            <button type="submit" form="saveForm" className="btn btn-success">Save</button>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default NavigationModal
