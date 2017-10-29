import React from 'react'

class ContentNav extends React.Component {
    render() {
        return (
            <div>
                <section className="content-header">
                    <h1>
                        Dashboard
                        <small>Version 2.0</small>
                    </h1>
                    <ol className="breadcrumb">
                        <li><a href="#"><i className="fa fa-dashboard"></i> Home</a></li>
                        <li className="active">Dashboard</li>
                    </ol>
                </section>
            </div>
        )
    }
}

export default ContentNav
