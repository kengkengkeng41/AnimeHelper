export const getLocal = (name) => {
    return localStorage.getItem(name)
}

export const setLocal = (name, value) => {
    localStorage.setItem(name, value)
}

export const removeLocal = (name) => {
    localStrage.removeItem(name)
}

export const convertUrl = (url) => {
    return url
    // return url.replace('http://localhost:8081/','https://3058n421r5.picp.vip/')
}