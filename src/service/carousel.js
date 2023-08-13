import axios from '../utils/axios'

export function insertCarousel(params) {
    return axios.post('/carousel/insert', params);
}

export function selectCarousel() {
    return axios.get('/carousel/select');
}

export function deleteCarousel(id) {
    return axios.post('/carousel/delete/'+id);
}

