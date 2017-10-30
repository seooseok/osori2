import React from 'react'
import ContentNav from '../components/ContentNav'

import './privilege.css';

class Privilege extends React.Component {
    render() {
        return (
            <div>
                <ContentNav category="Users" name="Privilege" description="Set individual permissions for the user"/>
                <section className="content">
                </section>
            </div>
        )
    }
}

export default Privilege
