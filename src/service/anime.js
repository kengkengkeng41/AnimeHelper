import axios from '../utils/axios'
export function insertAnime(params) {
    return axios.post('/anime/insert', params);
}

export function insertPic(params) {
    return axios.post('/anime/insertPic', params);
}

export function deleteAnime(number) {
    return axios.post('/anime/delete/'+number);
}

export function updateAnime(params) {
    return axios.post('/anime/update', params);
}

export function updatePic(params) {
    return axios.post('/anime/updatePic', params);
}

export function selectAnime(number) {
    return axios.get('/anime/select/'+number);
}
// 游客
export function selectAnimefv(number) {
    return axios.get('/anime/selectfv/'+number);
}

export function findAllAnime() {
    return axios.get('/anime/all');
}

export function searchAnime(query) {
    return axios.get('/anime/search/'+query);
}

