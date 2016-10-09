/* Michael Neas
 */
import java.io.File;
import java.util.HashMap;
import jeff.ini.Ini;

public class ConfigLoader
{
	private Ini _ini;
	private HashMap<String, Space>  _spaces  = new HashMap<String, Space>();
	private HashMap<String, Portal> _portals = new HashMap<String, Portal>();
	private HashMap<String, Agent>  _agents  = new HashMap<String, Agent>();
	public ConfigLoader(File iniFile)
	{
		_ini = new Ini(iniFile);
	}
	  public Agent buildAll()
	  {
	    _buildSpaces();
	    _buildPortals();
	    _buildExits();
	    _buildDestinations();
	    _buildAgents();
	    return _selectStartAgent();
	  }

	  private void _buildSpaces()
		{
			for(String spaceName : _ini.keys("spaces"))
			{
				String description = _ini.get("spaces", spaceName);
				String image = _ini.get("images", spaceName);
				Space spaceInstance = new Space(spaceName, description, null, image);
				_spaces.put(spaceName, spaceInstance); 
			}
		}
	  private void _buildPortals()
	  {
		  for(String portalName : _ini.keys("portals"))
		  {
			  String description = _ini.get("portals", portalName);
			  Portal portalInstance = new Portal(portalName, description, null);
			  _portals.put(portalName, portalInstance);
			  
		  }
	  }
	  private void _buildExits()
	  {
		  for(String spaceName : _ini.keys("exits"))
		  {	
			  String portalName = _ini.get("exits", spaceName);
			  Space getSpace = _spaces.get(spaceName);
			  Portal exitPortal = _portals.get(portalName);
			  getSpace.setPortal(exitPortal);
		  }
	  }
	  private void _buildDestinations()
	  {
		  for(String makePortal : _ini.keys("destinations"))
		  {
			  Portal portal = _portals.get(makePortal);
			  String destination = _ini.get("destinations", makePortal);
			  Space spaceInstance = _spaces.get(destination);
			  if(spaceInstance == null)
				{
					System.out.println("Error Message: Spaces is null");
					System.exit(1);
				}
			  portal.setDestination(spaceInstance);
			  
		  }
	  }
	  private void _buildAgents()
	  {
		  for(String makeAgent : _ini.keys("agents"))
		  {
			  String locationName = _ini.get("agents", makeAgent);
			  Space space = _spaces.get(locationName); 
				if(space == null)
				{
					System.out.println("Error Message: Spaces is null");
					System.exit(1);
				}else
				{
					Agent agent = new Agent(makeAgent, space);
					_agents.put(makeAgent, agent);
				}
		  }
	  }
	  private Agent _selectStartAgent()
	  {
		  for(String makeAgent : _ini.keys("start"))
			{
				String agentName = _ini.get("start", makeAgent);
				Agent agent = _agents.get(agentName);
				if(agent == null)
				{
					System.out.println("Agent is null");
					System.exit(1);
				}else
				{
					return agent;
				}
			}
		  return null;
	  }
}
