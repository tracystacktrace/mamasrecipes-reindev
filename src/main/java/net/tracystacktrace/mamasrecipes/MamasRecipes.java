package net.tracystacktrace.mamasrecipes;

import com.fox2code.foxloader.config.ConfigEntry;
import com.fox2code.foxloader.loader.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.common.item.ItemStack;
import net.tracystacktrace.mamasrecipes.bridge.MainBridge;
import net.tracystacktrace.mamasrecipes.constructor.item.ItemDescription;
import net.tracystacktrace.mamasrecipes.crawler.folder.FolderCrawlerException;
import net.tracystacktrace.mamasrecipes.crawler.folder.FolderRecipesCrawler;
import net.tracystacktrace.mamasrecipes.game.ReIndevLocalizer;

import java.io.File;

public class MamasRecipes extends Mod {
    public static final MamasRecipesConfig CONFIG = new MamasRecipesConfig();

    @Override
    public void onPreInit() {
        this.setConfigObject(CONFIG);
        MainBridge.setLocalization(new ReIndevLocalizer());
    }

    @Override
    public void onPostInit() {
        if (MamasRecipes.CONFIG.readLocalFolder) {
            final File recipesFolder = new File(Minecraft.getInstance().getMinecraftDir(), "mamasrecipes");
            try {
                final FolderRecipesCrawler crawler = FolderRecipesCrawler.fromFolder(recipesFolder);
                if (crawler != null) {
                    crawler.initializeRecipes();
                }
            } catch (FolderCrawlerException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static ItemStack convert(ItemDescription description) {
        final ItemStack itemStack = new ItemStack(description.getItemID(), description.getCount(), description.getMeta());
        if (description.getDisplayName() != null) {
            itemStack.setItemName(description.getDisplayName());
        }
        return itemStack;
    }

    public static class MamasRecipesConfig {
        @ConfigEntry(configName = "Read from folder")
        public boolean readLocalFolder = true;
    }
}
