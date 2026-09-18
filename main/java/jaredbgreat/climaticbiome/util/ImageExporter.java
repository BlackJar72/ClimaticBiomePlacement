package jaredbgreat.climaticbiome.util;

import java.awt.Point;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class ImageExporter extends CommandBase {

	private static void ExportImage(byte[] bytes) {
		int[] ints = new int[bytes.length];
		for(int i = 0; i < bytes.length; i++) {
			ints[i] = bytes[i];
		}
		BufferedImage buffer = new BufferedImage(4097, 4097, BufferedImage.TYPE_BYTE_GRAY);
		WritableRaster raster = buffer.getRaster();
		ColorModel cm = new ComponentColorModel(ColorModel.getRGBdefault().getColorSpace(), false, true, Transparency.OPAQUE, DataBuffer.TYPE_BYTE); 
		//BufferedImage image = new BufferedImage(cm, raster, true, null);
		try {
			//ImageIO.write(image, "png", new File("HeightMap" + System.currentTimeMillis()  + ".png"));
			raster.setSamples(0, 0, 4097, 4097, 0, ints);
			ImageIO.write(buffer, "png", new File("HeightMap" + System.currentTimeMillis()  + ".png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void OutputHeightMap(World world) {
		int height, index;
		byte[] bytes = new byte[4097 * 4097];
		for(int i = -2048; i < 2049; i++)
			for(int j = -2048; j < 2049; j++) {
				height = world.getHeight(i, j);
				index = (((j + 2048) * 4097) + i + 2048);
				bytes[index] = (byte)height;				
			}
		world.getSaveHandler();
		ExportImage(bytes);
	}


	@Override
	public String getName() {
		return "cbexport";
	}


	@Override
	public String getUsage(ICommandSender sender) {
		return "/cbexport";
	}


	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		OutputHeightMap(server.getWorld(0));		
	}
	
}
