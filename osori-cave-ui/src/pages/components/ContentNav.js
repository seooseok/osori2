import React from 'react'

class ContentNav extends React.Component {
    render() {
        return (
            <div>
                <section className="content-header">
                    <h1>
                        {this.props.name}
                        <small>{this.props.description}</small>
                    </h1>
                    <ol className="breadcrumb">
                        <li><a href="#"><i className="fa fa-television"></i> {this.props.category} </a></li>
                        <li className="active">{this.props.name}</li>
                    </ol>
                </section>
            </div>
        )
    }
}

ContentNav.defaultProps = {
    description: ''
}



export default ContentNav
