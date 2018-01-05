import React from 'react'
import {bindActionCreators} from 'redux'
import {connect} from 'react-redux'
import ContentNav from '../components/ContentNav'
import {findAll} from "../../actions/navigation/navigation.list";
import {NavigationList, NavigationTree} from "."

import './navigation.css'

class Navigation extends React.Component {
    componentWillMount() {
        this.props.findAll();
    }

    render() {
        return (
            <div>
                <ContentNav category="Navigation" name="Navigation Configuration"
                            description="Manage URLs to verify permissions by tree structure"/>
                <section className="content">
                    <div className="row">
                        <div className="col-md-7">
                            <NavigationTree/>
                        </div>
                        <div className="col-md-5">
                            <NavigationList/>
                        </div>
                    </div>
                </section>
            </div>
        )
    }
}

const mapDispatchToProps = (dispatch) => bindActionCreators({findAll}, dispatch);

export default connect(null, mapDispatchToProps)(Navigation)

