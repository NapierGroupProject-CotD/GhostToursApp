package ghost

import grails.transaction.Transactional
import java.security.SecureRandom
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

@Transactional
class HashingService {

    public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1"
	
	public static final int SALT_BYTE_SIZE = 24
	public static final int HASH_BYTE_SIZE = 24
	public static final int PBKDF2_ITERATIONS = 1000
	
	public static final int ITERATION_INDEX = 0
	public static final int SALT_INDEX = 1
	public static final int PBKDF2_INDEX = 2
	
	
    def serviceMethod() {

    }
	
	def createHash(String password){
		return createHash(password.toCharArray())
	}
	def createHash(char[] password){
		
		// generate random salt
		SecureRandom random = new SecureRandom()
		byte[] salt = new byte[SALT_BYTE_SIZE]
		random.nextBytes(salt)
		
		//hash the password
		byte[] hash = pbkdf2(password, salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE)
		
		// format iterations:salt:hash
		return PBKDF2_ITERATIONS + ":" + toHex(salt) + ":" + toHex(hash)
	}
	
	//computes the hash of the password
	def pbkdf2(char[] password, byte[] salt, int iterations, int bytes){
		PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8)
		SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM)
		
		return skf.generateSecret(spec).getEncoded()
	}
	
	//converts a string of hexadecimal characters into a byte array
	def fromHex(String hex){
		byte[] binary = new byte[hex.length() / 2]
		for(int i = 0; i < binary.length; i++){
			binary[i] = (byte)Integer.parseInt(hex.substring(2*i, 2*i+2), 16)
		}
		
		return binary
	}
	
	//converts a byte array into a hexadecimal string
	def toHex(byte[] array){
        BigInteger bi = new BigInteger(1, array)
        String hex = bi.toString(16)
        int paddingLength = (array.length * 2) - hex.length()
        if(paddingLength > 0){
            return String.format("%0" + paddingLength + "d", 0) + hex
        } else {
            return hex;
        }
    }
	
	def validatePassword(String password, String correctHash){
		return validatePassword(password.toCharArray(), correctHash)
	}
	def validatePassword(char[] password, String correctHash){
		//decode the hash into its parameters
		String[] parameters = correctHash.split(":")
		int iterations = Integer.parseInt(parameters[ITERATION_INDEX])
		byte[] salt = fromHex(parameters[SALT_INDEX])
		byte[] hash = fromHex(parameters[PBKDF2_INDEX])
		
		// compute the hash of the provided password using the same salt, iteration count and hash length
		byte[] testHash = pbkdf2(password, salt, iterations, hash.length)
		
		//compare the hashes in constant time
		return slowEquals(hash, testHash);
	}
	def slowEquals(byte[] a, byte[] b){
		int diff = a.length ^ b.length
		for(int i = 0; i < a.length && i < b.length; i++){
			diff |= a[i]^b[i]
		}
		return diff==0
	}
}
