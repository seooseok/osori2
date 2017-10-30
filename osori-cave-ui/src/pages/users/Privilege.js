import React from 'react'
import ContentNav from '../components/ContentNav'

class Privilege extends React.Component {
    render() {
        return (
            <div>
                <ContentNav category="Users" name="Privilege" description="Set individual permissions for the user"
                            icon="fa-wrench"/>
                <section className="content">
                </section>
            </div>
        )
    }
}
