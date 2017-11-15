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
                        <li><i className="fa fa-television"></i> {this.props.category}</li>
                        <li className="active">{this.props.name}</li>
                    </ol>
                </section>
            </div>
        )
    }
}

ContentNav.defaultProps = {
    description: ''
};



export default ContentNav
