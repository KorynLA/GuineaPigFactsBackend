package com.factsdemo.guineaPigFacts.serviceTests;
public class UserServiceTest {
    /**
    @TestConfiguration
    static class FactServiceTestContextConfiguration {
        @Bean
        public UserService userService() {
            return new UserService();
        }
    }
    @Autowired
    UserService userService;
    @MockBean
    UserRepository userRepository;

    @Test
    public void userSaveOrUpdateTest() {
        userService.saveOrUpdateFact(fact1);
        Mockito.verify(userRepository, Mockito.times(1)).save(fact1);
    }
    @Test
    public void userFindAllTest(){
        Mockito.when(userRepository.findAll()).thenReturn(facts);
        ArrayList<Fact> foundFacts = (ArrayList<Fact>) userService.findAll();
        assertEquals(2, foundFacts.size());
    }

    @Test
    public void userDeleteByIdTest() {
        userService.delete(fact1.getId());
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(fact1.getId());
    }
    @Test
    public void userFindByIdTest() {
        Mockito.when(userRepository.findById(fact1.getId())).thenReturn(Optional.of(fact1));
        Optional<Fact> found = userService.findById(idVal1);
        assertTrue(found.isPresent());
    }
    @Test
    public void findByUsernameTest() {}

    @Test
    public void findByEmailTest() {}

    @Test
    public void getCurrentUserTest() {}

    @Test
    public void updateUserTest() {}
    **/
}
