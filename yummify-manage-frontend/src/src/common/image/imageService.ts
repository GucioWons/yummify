import axios from "axios";

export const imageService = {
    async fetchImageFile(url: string) {
        return axios.get(url, {responseType: "blob"})
            .then(response => new File([response.data], "dish-image.jpg", {type: response.data.type}));
    }
}