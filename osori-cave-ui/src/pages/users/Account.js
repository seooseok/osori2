import React from 'react'
import ContentNav from '../components/ContentNav'

class Account extends React.Component {
    render() {
        return (
            <div>
                <ContentNav category="Users" name="Account" description="Set personal information for the user"
                            icon="fa-user"/>
                <section className="content">
                </section>
            </div>
        )
    }
}
