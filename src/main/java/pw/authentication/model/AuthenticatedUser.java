package pw.authentication.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("authenticatedUser")
public class AuthenticatedUser
{
	private static final long serialVersionUID = 5588914462123783411L;
	private String countryCode;
	private String firstName;
	private Long id;
	private String lastName;
	private String notificationEmail;
	@XStreamOmitField
	private String password;
	private String pwId;
	private List<String> emailAddresses = new ArrayList<String>();
	private List<String> verifiedEmails = new ArrayList<String>();
	private List<String> domains = new ArrayList<String>();
	private String languagePreference;
	private List<String> adminDomains = new ArrayList<String>();
	private UUID uuid;
	private Map<String, String> photoUrls;
	private String profileUrl;

	public static final String INCLUDE_DOMAINS = "domains";
	public static final String INCLUDE_VERFIED_EMAILS = "verifiedEmails";
	private static final String EMPTY_STRING = "";

	public String getFirstName()
	{
		return firstName;
	}

	public Long getId()
	{
		return id;
	}

	public String getLastName()
	{
		return lastName;
	}

	public String getNotificationEmail()
	{
		return notificationEmail;
	}

	public String getPassword()
	{
		return password;
	}

	public String getPwId()
	{
		return pwId;
	}

	public void setCountryCode(final String countryCode)
	{
		this.countryCode = countryCode;
	}

	public void setFirstName(final String firstName)
	{
		this.firstName = firstName;
	}

	public void setId(final Long id)
	{
		this.id = id;
	}

	public void setLastName(final String lastName)
	{
		this.lastName = lastName;
	}

	public void setNotificationEmail(final String notificationEmail)
	{
		this.notificationEmail = notificationEmail;
	}

	public void setPassword(final String password)
	{
		this.password = password;
	}

	public void setPwId(final String pwId)
	{
		this.pwId = pwId;
	}

	public void setUserId(final Long id)
	{
		this.id = id;
	}

	public String getFullName()
	{
		{
			final List<String> nameElements = Arrays.asList(firstName, lastName);
			String fullName = EMPTY_STRING;
			for (final String name : nameElements)
			{
				fullName += name != null ? name + " " : EMPTY_STRING;
			}
			return fullName.trim();
		}
	}

	public List<String> getEmailAddresses()
	{
		return emailAddresses;
	}

	public void setEmailAddresses(final List<String> emailAddresses)
	{
		this.emailAddresses = emailAddresses;
	}

	public void setVerifiedEmails(final List<String> verifiedEmails)
	{
		this.verifiedEmails = verifiedEmails;
	}

	//	@Override
	//	public boolean equals(final Object other)
	//	{
	//		if (this == other)
	//		{
	//			return true;
	//		}
	//		if (other == null)
	//		{
	//			return false;
	//		}
	//		if (!this.getClass().getName().equals(other.getClass().getName()))
	//		{
	//			return false;
	//		}
	//		return equals((UserStub) other);
	//	}
	//
	//	private boolean equals(final UserStub other)
	//	{
	//		return pwId.equals(other.pwId) && notificationEmail.equals(other.notificationEmail)
	//				&& firstName.equals(other.firstName) && lastName.equals(other.lastName)
	//				&& countryCode.equals(other.countryCode);
	//	}

	public String getCountryCode()
	{
		return countryCode;
	}

	public List<String> getDomains()
	{
		return domains;
	}

	public void setDomains(final List<String> domains)
	{
		this.domains = domains;
	}

	public List<String> getVerifiedEmails()
	{
		return verifiedEmails;
	}

	public boolean isPartOf(final String domainName)
	{
		return domains.contains(domainName);
	}

	public void setLanguagePreference(final String languagePreference)
	{
		this.languagePreference = languagePreference;
	}

	public String getLanguagePreference()
	{
		return languagePreference;
	}

	public List<String> getAdminDomains()
	{
		return adminDomains;
	}

	public void setAdminDomains(final List<String> adminDomains)
	{
		this.adminDomains = adminDomains;
	}

	public UUID getUuid()
	{
		return uuid;
	}

	public void setUuid(final UUID uuid)
	{
		this.uuid = uuid;
	}

	public void setPhotoUrls(final Map<String, String> photoUrls)
	{
		this.photoUrls = photoUrls;
	}

	public Map<String, String> getPhotoUrls()
	{
		return photoUrls;
	}

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }
}
