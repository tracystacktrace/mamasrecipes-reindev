package net.tracystacktrace.mamasrecipes.game;

import com.fox2code.foxloader.config.ConfigEntry;
import com.fox2code.foxloader.loader.Mod;
import com.fox2code.foxloader.loader.ModLoader;
import net.minecraft.common.item.ItemStack;
import net.tracystacktrace.mamasrecipes.constructor.item.ItemDescription;
import net.tracystacktrace.mamasrecipes.crawler.CrawlerException;
import net.tracystacktrace.mamasrecipes.crawler.impl.FolderRecipesCrawler;
import net.tracystacktrace.mamasrecipes.crawler.impl.ZipRecipesCrawler;

import java.io.File;
import java.io.IOException;
import java.util.zip.ZipFile;

public class MamasRecipes extends Mod {
    public static final ReIndevLocalizer LOCALIZER = new ReIndevLocalizer();
    public static final MamasRecipesConfig CONFIG = new MamasRecipesConfig();

    @Override
    public void onPreInit() {
        this.setConfigObject(CONFIG);
    }

    @SuppressWarnings("ForLoopReplaceableByForEach")
    @Override
    public void onPostInit() {
        if (MamasRecipes.CONFIG.readLocalFolder) {
            final File recipesFolder = new File(ModLoader.getConfigFolder(), "mamasrecipes");
            final File[] zipFiles = ZipIOHelper.collectZipFiles(recipesFolder);

            for (int i = 0; i < zipFiles.length; i++) {
                try (final ZipFile zipFile = new ZipFile(zipFiles[i])) {
                    final ZipRecipesCrawler crawler = ZipRecipesCrawler.fromZip(LOCALIZER, zipFile);
                    crawler.initializeRecipes(LOCALIZER);
                } catch (IOException | CrawlerException e) {
                    System.out.println("Failed to process zip recipes file: " + zipFiles[i].getName());
                    e.printStackTrace();
                }
            }

            try {
                final FolderRecipesCrawler crawler = FolderRecipesCrawler.fromFolder(LOCALIZER, recipesFolder);
                if (crawler != null) {
                    crawler.initializeRecipes(LOCALIZER);
                }
            } catch (CrawlerException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static ItemStack convertStrict(ItemDescription description) {
        final ItemStack itemStack = new ItemStack(getAsInt(description.getItemIdentifier()), description.getCount(), description.getMeta());
        if (description.getDisplayName() != null) {
            itemStack.setItemName(description.getDisplayName());
        }
        return itemStack;
    }

    public static Object convertLoose(ItemDescription description) {
        final String id = description.getItemIdentifier();
        //process oreDict
        if (id.startsWith("$oreDict:")) {
            final String data = id.split(":")[1];
            return ReIndevOreDict.getFromList(data);
        }
        return convertStrict(description);
    }

    static int getAsInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static class MamasRecipesConfig {
        @ConfigEntry(configComment = "Read from local folder")
        public boolean readLocalFolder = true;
    }
}
