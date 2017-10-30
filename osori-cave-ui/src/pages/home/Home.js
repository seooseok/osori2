import React from 'react'
import ContentNav from "../components/ContentNav";

import './home.css'

class Home extends React.Component {
    render() {
        return (
            <div>
                <ContentNav category="Home" name="Dashboard" description="version 2.0" icon="fa-dashboard"/>
                <section className="content">
                </section>
            </div>
        )
    }
}

export default Home
