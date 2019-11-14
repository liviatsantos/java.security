
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Arrays;

public class DigestDefault {
	protected byte[] digest;
	protected MessageDigest md;
	
	protected DigestDefault(String password, String algorithm, String provider) throws GeneralSecurityException {
		if (provider == null)
			md = MessageDigest.getInstance(algorithm);
		else
			md = MessageDigest.getInstance(algorithm, provider);
		
		digest = md.digest(password.getBytes());
	}
	
	public static DigestDefault getInstance(String password, String algorithm) throws GeneralSecurityException {
		return new DigestDefault(password, algorithm, null);
	}
	
	public int getDigestSize() {
		return digest.length;
	}
		
	public String getDigestAsHexString() {
		return new BigInteger(1, digest).toString(16);
	}
	
	public boolean checkPassword(String password) {
		return Arrays.equals(digest, md.digest(password.getBytes()));
	}
	
	public static void main (String[] args) {
		
			DigestDefault app = null;
			String algorithm = "SHA-256";
			try {
				app = getInstance("teste", algorithm);
			} catch (GeneralSecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Digest usando: " + algorithm + " Tamanho bytes: " + app.getDigestSize());
			System.out.println("Digest: " + app.getDigestAsHexString());
			System.out.println("A senha é 'teste'? "+ app.checkPassword("teste"));
			System.out.println("A senha é 'secret'? " + app.checkPassword("secret"));
		
	}
}
