package control;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;
import javax.naming.NamingException;

import org.hibernate.annotations.common.util.impl.LoggerFactory;

import data.Data;
import entity.Reaction;
import entity.Sharing;
import entity.SharingType;
import session.ManageReactionsRemote;
import session.ManageSharingsRemote;
import control.Client;

public class SharingsControl {

	public static void getAllSharingsOfGroup() {
		Data.sharings = new ArrayList<Sharing>();
		ManageSharingsRemote remote;
		try {
			remote = (ManageSharingsRemote) Client.getContext()
					.lookup("ejb:ShareAroundEAR/ShareAroundServer//ManageSharings!session.ManageSharingsRemote");
			Data.sharings = remote.getNewSharings(Data.group);

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void getAllSharingsOfGroup(SharingType sharingType) {
		Data.sharings = new ArrayList<Sharing>();
		ManageSharingsRemote remote;
		try {
			remote = (ManageSharingsRemote) Client.getContext()
					.lookup("ejb:ShareAroundEAR/ShareAroundServer//ManageSharings!session.ManageSharingsRemote");
			Data.sharings = remote.getNewSharingsByType(sharingType, Data.group);

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	public static void getAllSharingsOfUser(SharingType sharingType) {
		Data.sharings = new ArrayList<Sharing>();
		ManageSharingsRemote remote;
		try {
			remote = (ManageSharingsRemote) Client.getContext()
					.lookup("ejb:ShareAroundEAR/ShareAroundServer//ManageSharings!session.ManageSharingsRemote");
			Data.sharings = remote.getNewSharingsByUserAndType(sharingType, Data.user);

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void getAllSharingsOfUser() {
		Data.sharings = new ArrayList<Sharing>();
		ManageSharingsRemote remote;
		try {
			remote = (ManageSharingsRemote) Client.getContext()
					.lookup("ejb:ShareAroundEAR/ShareAroundServer//ManageSharings!session.ManageSharingsRemote");
			Data.sharings = remote.getSharingsByUser(Data.user);

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void setInvalid(Sharing sharing) {

		ManageSharingsRemote remote;
		try {
			remote = (ManageSharingsRemote) Client.getContext()
					.lookup("ejb:ShareAroundEAR/ShareAroundServer//ManageSharings!session.ManageSharingsRemote");
			int updateCount = remote.setInvalid(sharing);

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void addNewSharing(Sharing sharing) {
		ManageSharingsRemote remote;
		try {
			remote = (ManageSharingsRemote) Client.getContext()
					.lookup("ejb:ShareAroundEAR/ShareAroundServer//ManageSharings!session.ManageSharingsRemote");
			remote.addSharing(sharing);

		} catch (NamingException e) {

			e.printStackTrace();
		}

	}

	public static void getTagsOfImage(String encodedImg) {
		ManageSharingsRemote remote;

		try {
			remote = (ManageSharingsRemote) Client.getContext()
					.lookup("ejb:ShareAroundEAR/ShareAroundServer//ManageSharings!session.ManageSharingsRemote");
			Data.tags = remote.getTagsOfImage(encodedImg);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
