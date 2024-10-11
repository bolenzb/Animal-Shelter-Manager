package com.techelevator.dao;

import com.techelevator.model.PendingUser;
import com.techelevator.model.dto.SubmitPendingUserDto;

import java.util.List;

public interface PendingUserDao {
    PendingUser getApplicantById(int id);
    PendingUser getApplicantByEmail(String email);
    List<PendingUser> getPendingApplicants();
    List<PendingUser> getAllApplicants();
    void updateApplication(int statusId, int pendingId);
    PendingUser createPendingUser(SubmitPendingUserDto Applicant);
}
