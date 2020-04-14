package ro.secret.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.secret.entity.Employer;
import ro.secret.entity.Pair;
import ro.secret.exception.EmployerExtractException;
import ro.secret.repositories.EmployerRepository;
import ro.secret.repositories.PairRepository;

import java.util.List;
import java.util.Random;

@Service
public class ExtractService {
    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private PairRepository pairRepository;

    @Autowired
    private EmployerService employerService;

    public void extract (int employerId) throws EmployerExtractException {
        List<Integer> eligibleIds = employerRepository.eligibleEmployerIds(employerId);
        if (eligibleIds.size() > 0) {
            createPair(employerId, eligibleIds);
        }
        else{
            throw new EmployerExtractException ("Can't choose yourself");
        }
    }

    private void createPair(int employerId, List<Integer> eligibleIds)
    {
        Random random = new Random();
        Integer fromId = eligibleIds.get(random.nextInt(eligibleIds.size()));
        Pair pair = new Pair();
        pair.setToId(employerId);
        pair.setFromId(fromId);
        pairRepository.save(pair);

        Employer employer = employerService.getById(employerId);
        employer.setExtract(true);
        employerService.save(employer);
    }
}
