import React from 'react'
import ContentNav from '../components/ContentNav'
import AccountSearchComponent from './AccountSearchComponent'

import './account.css'

class Account extends React.Component {
    render() {
        return (
            <div>
                <ContentNav category="Users" name="Account" description="Set personal information for the user"/>
                <section className="content">
                    <AccountSearchComponent/>
                </section>
            </div>
        )
    }
}

export default Account
