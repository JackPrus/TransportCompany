package by.prus.finalproject.dao.mysql;

//import by.prus.finalproject.bean.User;
//import by.prus.finalproject.dao.UserDao;
//import by.prus.finalproject.exception.PersistentException;
//import by.prus.finalproject.service.mapper.UserRowMapper;
//
//import java.sql.Connection;
//import java.util.Optional;

//public class UserDaoImpl extends AbstractDao<User> implements UserDao {
//    private static final String FIND_USER_BY_LOGIN_AND_ID = "SELECT * FROM user WHERE login = ? and password = ?";
//    private static final String SAVE_QUERY = "INSERT INTO user (id,login,password,role,is_blocked) values (?,?,?,?,?)";
//    private static final String UPDATE_QUERY = "UPDATE user SET login=?, password=?,role=?,is_blocked=? WHERE id = ?";
//    //  private  final String TABLE_NAME = "user";
//
//    public UserDaoImpl(Connection connection) {
//        super(connection, new UserRowMapper(), "user", new UserParser());
//
//    }
//
//    @Override
//    public Optional<User> findUserByLoginAndPassword(String login, String password) throws PersistentException {
//        return executeForSingleResult(FIND_USER_BY_LOGIN_AND_ID, login, password);
//    }
//
//    @Override
//    protected String getSaveQuery() {
//        return SAVE_QUERY;
//    }
//
//    @Override
//    protected String getUpdateQuery() {
//        return UPDATE_QUERY;
//    }
//
//    @Override
//    public Integer create(User entity) throws PersistentException {
//        return null;
//    }
//
//    @Override
//    public User read(Integer identity) throws PersistentException {
//        return null;
//    }
//
//    @Override
//    public void delete(Integer identity) throws PersistentException {
//
//    }
//
//    @Override
//    public void update(User entity) throws PersistentException {
//
//    }
//}
