import PropTypes from 'prop-types';
import React from 'react';
import {
    ViewPropTypes,
} from 'react-native';
import decorateMapComponent, {
    USES_DEFAULT_IMPLEMENTATION, NOT_SUPPORTED,
} from './decorateMapComponent';

const propTypes = {
    ...ViewPropTypes,

    /**
     * The raw GeoJson string to parse and render
     */
    raw: PropTypes.string,

    /**
     * The stroke width to use for the path.
     */
    strokeWidth: PropTypes.number,
};

const defaultProps = {
    strokeWidth: 1,
};

class MapGeoJson extends React.Component {
    setNativeProps(props) {
        this.geoJson.setNativeProps(props);
    }

    render() {
        const AIRMapGeoJson = this.getAirComponent();
        return (
            <AIRMapGeoJson {...this.props} ref={ref => { this.geoJson = ref; }} />
        );
    }
}

MapGeoJson.propTypes = propTypes;
MapGeoJson.defaultProps = defaultProps;

module.exports = decorateMapComponent(MapGeoJson, {
    componentType: 'GeoJson',
    providers: {
        google: {
            ios: NOT_SUPPORTED,
            android: USES_DEFAULT_IMPLEMENTATION,
        },
    },
});
