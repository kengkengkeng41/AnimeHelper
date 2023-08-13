import axios from '../utils/axios'

export function insertLinks(params) {
    return axios.post('/links/insert', params);
}

export function selectLinks(number) {
    return axios.get('/links/select/'+number);
}

export function selectAll() {
    return axios.get('/links/selectAll');
}

export function deleteLinks(links) {
    return axios.post('/links/delete/'+links);
}

