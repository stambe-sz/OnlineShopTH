package onlineshop.service;

import onlineshop.model.service.ImageServiceModel;

public interface ImageService {
    boolean addImage(ImageServiceModel imageServiceModel);

    ImageServiceModel updateImage(long imageId);

    ImageServiceModel deleteImage(long imageId);

    ImageServiceModel findAllMyImages(long userId);
}
