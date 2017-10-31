import React from 'react'
import ContentNav from '../components/ContentNav'
import {AccountDetail, AccountList, AccountSearch} from './'

import './account.css'

class Account extends React.Component {
    render() {
        return (
            <div>
                <ContentNav category="Users" name="Account" description="Set personal information for the user"/>
                <section className="content">
                    <AccountSearch/>
                    <div className="row">
                        <div className="col-md-8">
                            <AccountList/>
                        </div>
                        <div className={"col-md-4"}>
                            <AccountDetail/>
                        </div>
                    </div>

                </section>
            </div>
        )
    }
}

export default Account
