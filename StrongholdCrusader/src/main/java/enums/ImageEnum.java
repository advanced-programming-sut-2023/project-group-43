package enums;

import javafx.scene.image.Image;

public enum ImageEnum {
    //<<BACKGROUND>>
    MAIN_MENU(new Image(ImageEnum.class.getResource("").toExternalForm())),
    SHOP_MENU(new Image(ImageEnum.class.getResource("/images/background/store.jpg").toExternalForm())),
    WOOD_MENU(new Image(ImageEnum.class.getResource("/images/background/wood.png").toExternalForm())),
    OLD_PAPER(new Image(ImageEnum.class.getResource("/images/background/oldPaper.png").toExternalForm())),

    //<<MATERIAL>>
    STONE(new Image(ImageEnum.class.getResource("/images/material/Stone.png").toExternalForm())),
    WOOD(new Image(ImageEnum.class.getResource("/images/material/wood.jpg").toExternalForm())),
    IRON(new Image(ImageEnum.class.getResource("/images/material/iron.png").toExternalForm())),
    TAR(new Image(ImageEnum.class.getResource("/images/material/tar.png").toExternalForm())),

    WHEAT(new Image(ImageEnum.class.getResource("/images/material/wheat.png").toExternalForm())),
    APPLE(new Image(ImageEnum.class.getResource("/images/material/apple.png").toExternalForm())),
    CHEESE(new Image(ImageEnum.class.getResource("/images/material/cheese.png").toExternalForm())),
    MEAT(new Image(ImageEnum.class.getResource("/images/material/meat.png").toExternalForm())),
    FROZEN_MEAT(new Image(ImageEnum.class.getResource("/images/material/frozenMeat.png").toExternalForm())),
    BREAD(new Image(ImageEnum.class.getResource("/images/material/bread.png").toExternalForm())),
    HOP(new Image(ImageEnum.class.getResource("/images/material/hop.png").toExternalForm())),
    FLOUR(new Image(ImageEnum.class.getResource("/images/material/flour.png").toExternalForm())),
    BEER(new Image(ImageEnum.class.getResource("/images/material/beer.png").toExternalForm())),

    BOW(new Image(ImageEnum.class.getResource("/images/material/bow.png").toExternalForm())),
    SWORD(new Image(ImageEnum.class.getResource("/images/material/sword.png").toExternalForm())),
    SPEAR(new Image(ImageEnum.class.getResource("/images/material/spear.png").toExternalForm())),
    CUDGEL(new Image(ImageEnum.class.getResource("/images/material/cudgel.png").toExternalForm())),
    GRENADE(new Image(ImageEnum.class.getResource("/images/material/grenade.png").toExternalForm())),
    HOOK(new Image(ImageEnum.class.getResource("/images/material/hook.png").toExternalForm())),
    TORCH(new Image(ImageEnum.class.getResource("/images/material/torch.png").toExternalForm())),
    GRAVEL(new Image(ImageEnum.class.getResource("/images/material/gravel.png").toExternalForm())),

    LADDER(new Image(ImageEnum.class.getResource("/images/material/ladder.png").toExternalForm())),
    ARMOUR(new Image(ImageEnum.class.getResource("/images/material/armour.png").toExternalForm())),

    HORSE(new Image(ImageEnum.class.getResource("/images/material/horse.png").toExternalForm())),
    PEASANT(new Image(ImageEnum.class.getResource("/images/material/peasant.png").toExternalForm())),
    UNIT(new Image(ImageEnum.class.getResource("").toExternalForm())),

    //stickers
    COIN(new Image(ImageEnum.class.getResource("/images/material/coin.png").toExternalForm()));

    public Image image;

    ImageEnum(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }
    }
