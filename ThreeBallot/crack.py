from util import is_legal_triplet, strips_from_triplets, generate_triplets
from pprint import pprint


# For given strip, finds first valid pair, which makes up a valid triplet together
# with that strip
def find_valid_triplet(strip, strip_id, published_strips, used_strips):
    for i in range(len(published_strips)):
        if i == strip_id or used_strips[i]:
            continue
        for j in range(i + 1, len(published_strips)):
            if j == strip_id or used_strips[j]:
                continue

            assert strip_id < i < j
            triplet = [strip, published_strips[i], published_strips[j]]
            if is_legal_triplet(triplet):
                used_strips[strip_id] = used_strips[i] = used_strips[j] = 1
                return triplet
    return None


def crack_votes(published_strips):
    cracked_triplets = []
    used_strips = [0] * len(published_strips)
    for id, strip in enumerate(published_strips):

        # value other than 0 means strip is already used
        if used_strips[id] != 0:
            continue

        triplet = find_valid_triplet(strip, id, published_strips, used_strips)
        if triplet is not None:
            cracked_triplets.append(sorted(triplet))

    return sorted(cracked_triplets)


if __name__ == "__main__":
    total_options = 10
    total_voters = 100

    triplets = generate_triplets(total_options, total_voters)
    published_strips = strips_from_triplets(triplets)

    print("\nTriplets:")
    pprint(triplets)

    cracked_triplets = crack_votes(published_strips)
    print("Done cracking")
    print("\ncracked triplets:")
    pprint(cracked_triplets)

    total = len(triplets)
    cracked = 0

    for triplet in cracked_triplets:
        if triplet in triplets:
            cracked += 1

    print("Was it cracked?", cracked, total, cracked / total)
