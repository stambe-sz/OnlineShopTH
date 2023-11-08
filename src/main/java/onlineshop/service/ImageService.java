package onlineshop.service;

import onlineshop.model.service.ImageServiceModel;

public interface ImageService {
    boolean addImage(ImageServiceModel imageServiceModel);

    ImageServiceModel updateImage(Long imageId);

    ImageServiceModel deleteImage(Long imageId);

    ImageServiceModel findAllMyImages(Long userId);
}
