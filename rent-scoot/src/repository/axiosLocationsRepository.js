import axiosCustom from '../custom-axios/axios'
//import qs from 'qs'

const LocationsService = {
    fetchLocations : () => {
        return axiosCustom.get("/api/rental/locations");
    },
    searchLocation : (term) => {
        return axiosCustom.get(`/api/rental/locations?term=${term}`)
    }
}

export default LocationsService;