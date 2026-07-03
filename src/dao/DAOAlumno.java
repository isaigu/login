package dao;

import modelo.Alumno;
import conexion.ConexionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOAlumno
{
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private Alumno alumno;

    public ArrayList<Alumno> listar()
    {
        ArrayList<Alumno>list = new ArrayList<>();
        String sql="SELECT * FROM alumnos";
        try
        {
            con=ConexionMySQL.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next())
            {
                alumno=new Alumno();
                alumno.setNL(rs.getInt("NL"));
                alumno.setNombre(rs.getString("Nombre"));
                alumno.setPaterno(rs.getString("Paterno"));
                alumno.setMaterno(rs.getString("Materno"));
                alumno.setDDI(rs.getDouble("DDI"));
                alumno.setDWI(rs.getDouble("DWI"));
                alumno.setECBD(rs.getDouble("ECBD"));
                list.add(alumno);
            }
            rs.close();
            ps.close();
            con.close();
        }
        catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public String mostrar()
    {
        String r, fila;

        r ="""
                <div class="card shadow-sm">
                    <div class="card-body">
                        <h3 class="card-title mb-3">Lista de Alumnos</h3>
                        <div class="table-responsive">
                            <table class="table table-striped table-hover align-middle">
                                <thead class="table-dark">
                                    <tr>
                                        <th>NL</th>
                                        <th>Nombre</th>
                                        <th>Paterno</th>
                                        <th>Materno</th>
                                        <th>DDI</th>
                                        <th>DWI</th>
                                        <th>ECBD</th>
                                        <th>PROM</th>
                                        <th colspan="2">Opciones</th>
                                    </tr>
                                </thead>
                                <tbody>
            """;

        for(Alumno reg : listar())
        {
            /*
            r= r + "<tr>\n"
            + "        <td>" + reg.getNL() + "</td>\n"
            + "        <td>" + reg.getNombre() + "</td>\n"
            + "        <td>" + reg.getPaterno() + "</td>\n"
            + "        <td>" + reg.getMaterno() + "</td>\n"
            + "        <td>\n"
            + "            <form method='post'>\n"   // formulario editar
            + "                <input type='hidden' name='accion' value='Editar'/>\n"
            + "                <input type='hidden' name='tfNL' value='" + reg.getNL() + "'/>\n"
            + "                <input type='submit' value='Editar'>\n"  //+ "    <button type='submit'>Editar</button>"
            + "            </form>\n"
            + "        </td>\n"
            + "        <td>\n"
            + "            <form method='post'>\n"   // formulario eliminar
            + "                <input type='hidden' name='accion' value='Eliminar'/>\n"
            + "                <input type='hidden' name='tfNL' value='" + reg.getNL() + "'/>\n"
            + "                <input type='submit' value='Eliminar'>\n"
            + "            </form>\n"
            + "        </td>\n"
            + "    </tr>\n";
            */

            fila ="""
                                    <tr>
                                        <td>%d</td>
                                        <td>%s</td>
                                        <td>%s</td>
                                        <td>%s</td>
                                        <td>%.2f</td>
                                        <td>%.2f</td>
                                        <td>%.2f</td>
                                        <td>%.2f</td>
                                        <td>
                                            <form method="post" action="SAlumno">
                                                <input type="hidden" name="accion" value="Editar"/>
                                                <input type="hidden" name="tfNL" value="%d"/>
                                                <button type="submit" class="btn btn-sm btn-warning">Editar</button>
                                            </form>
                                        </td>
                                        <td>
                                            <form method="post" action="SAlumno">
                                                <input type="hidden" name="accion" value="Eliminar"/>
                                                <input type="hidden" name="tfNL" value="%d"/>
                                                <button type="submit" class="btn btn-sm btn-danger">Eliminar</button>
                                            </form>
                                        </td>
                                    </tr>
                """;

            r = r + String.format(fila, reg.getNL(), reg.getNombre(), reg.getPaterno(), reg.getMaterno(),
                    reg.getDDI(), reg.getDWI(), reg.getECBD(), reg.getProm(), reg.getNL(), reg.getNL());
        }
        r = r + """
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            """;
        return r;
    }

    public Alumno buscar(int nL)
    {
        alumno = null;
        String sql="SELECT * FROM alumnos WHERE NL = ?";
        try
        {
            con=ConexionMySQL.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, nL);
            rs=ps.executeQuery();

            while(rs.next())
            {
                alumno=new Alumno();
                alumno.setNL(rs.getInt("NL"));
                alumno.setNombre(rs.getString("Nombre"));
                alumno.setPaterno(rs.getString("Paterno"));
                alumno.setMaterno(rs.getString("Materno"));
                alumno.setDDI(rs.getDouble("DDI"));
                alumno.setDWI(rs.getDouble("DWI"));
                alumno.setECBD(rs.getDouble("ECBD"));
            }
            rs.close();
            ps.close();
            con.close();
        }
        catch (SQLException e) {}
        return alumno;
    }

    public boolean agregar(Alumno alumno)
    {
        String sql="INSERT INTO alumnos (NL, Nombre, Paterno, Materno, DDI, DWI, ECBD) VALUES (?,?,?,?,?,?,?)";

        try
        {
            con=ConexionMySQL.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, alumno.getNL());
            ps.setString(2, alumno.getNombre());
            ps.setString(3, alumno.getPaterno());
            ps.setString(4, alumno.getMaterno());
            ps.setDouble(5, alumno.getDDI());
            ps.setDouble(6, alumno.getDWI());
            ps.setDouble(7, alumno.getECBD());
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException e) { e.printStackTrace(); }
        return true;
    }

    public boolean editar(Alumno alumno,int old)
    {
        String sql="UPDATE alumnos SET NL = ?, Nombre = ?, Paterno = ?, Materno = ?, DDI = ?, DWI = ?, ECBD = ? WHERE NL = ?";

        try
        {
            con=ConexionMySQL.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, alumno.getNL());
            ps.setString(2, alumno.getNombre());
            ps.setString(3, alumno.getPaterno());
            ps.setString(4, alumno.getMaterno());
            ps.setDouble(5, alumno.getDDI());
            ps.setDouble(6, alumno.getDWI());
            ps.setDouble(7, alumno.getECBD());
            ps.setInt(8, old);
            ps.executeUpdate();
            ps.close();
            con.close();
        }
        catch (SQLException e) { e.printStackTrace(); }
        return true;
    }

    public boolean eliminar(int nL)
    {
        String sql="DELETE FROM alumnos WHERE NL = ?";
        try
        {
            con=ConexionMySQL.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, nL);
            ps.executeUpdate();
            ps.close();
            con.close();
        }
        catch (SQLException e) { e.printStackTrace(); }
        return true;
    }
}
